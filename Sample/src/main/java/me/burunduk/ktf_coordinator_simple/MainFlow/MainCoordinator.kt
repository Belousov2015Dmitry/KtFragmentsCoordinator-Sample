/*
 * Copyright (C) 2017 Dmitry Belousov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.burunduk.ktf_coordinator_simple.MainFlow

import me.burunduk.ktf_coordinator.BaseCoordinator
import me.burunduk.ktf_coordinator.Coordinatable
import me.burunduk.ktf_coordinator.CoordinatorHandler
import me.burunduk.ktf_coordinator.Routable
import me.burunduk.ktf_coordinator_simple.FinalFlow.FinalCoordinator
import me.burunduk.ktf_coordinator_simple.FinalFlow.FinalCoordinatorsFactory
import me.burunduk.ktf_coordinator_simple.FinalFlow.FinalFragmentsFactory
import java.util.HashSet

/**
 * Created by Dmitry Belousov on 07.09.17.
 */


class MainCoordinator(
        override var router: Routable,
        override var coordinatorsFactory: MainCoordinatorsFactory,
        override var fragmentsFactory: MainFragmentsFactory
)
    : BaseCoordinator<MainCoordinatorsFactory, MainFragmentsFactory>()
{
    override var completionHandler: CoordinatorHandler? = null

    override val childCoordinators = HashSet<Coordinatable>(1)


    override fun start() {
        showRedFragment()
    }


    private fun showRedFragment() {
        fragmentsFactory.makeRedFragment().let {
            it.completionHandler = { showGreenFragment() }
            router.push(it)
        }
    }

    private fun showGreenFragment() {
        fragmentsFactory.makeGreenFragment().let {

            it.completionHandler = { greenResult ->
                when(greenResult) {
                    GreenResult.Back -> router.pop()

                    GreenResult.Done -> showFinalFlow()
                }
            }

            router.push(it)
        }
    }

    private fun showFinalFlow() {
        coordinatorsFactory.makeFinalCoordinator(router).let {
            addChild(coordinator = it)

            it.completionHandler = {
                removeChild(coordinator = it)
                router.pop()
            }

            it.start()
        }
    }
}


class MainCoordinatorsFactory
{
    fun makeFinalCoordinator(router: Routable) = FinalCoordinator(
            router = router,
            coordinatorsFactory = FinalCoordinatorsFactory(),
            fragmentsFactory = FinalFragmentsFactory()
    )
}


class MainFragmentsFactory
{
    fun makeRedFragment() = RedFragment.newInstance()

    fun makeGreenFragment() = GreenFragment.newInstance()
}