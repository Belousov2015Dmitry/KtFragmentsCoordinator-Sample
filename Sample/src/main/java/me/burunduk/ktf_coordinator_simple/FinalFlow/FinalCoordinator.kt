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

package me.burunduk.ktf_coordinator_simple.FinalFlow

import me.burunduk.ktf_coordinator.BaseCoordinator
import me.burunduk.ktf_coordinator.Coordinatable
import me.burunduk.ktf_coordinator.CoordinatorHandler
import me.burunduk.ktf_coordinator.Routable

/**
 * Created by Dmitry Belousov on 07.09.17.
 */


class FinalCoordinator(
        override var router: Routable,
        override var coordinatorsFactory: FinalCoordinatorsFactory,
        override var fragmentsFactory: FinalFragmentsFactory
)
    : BaseCoordinator<FinalCoordinatorsFactory, FinalFragmentsFactory>()
{
    override var completionHandler: CoordinatorHandler? = null

    override val childCoordinators = HashSet<Coordinatable>(0)


    override fun start() {
        fragmentsFactory.makeFinalFragment().let {

            it.completionHandler = {
                this.completionHandler?.invoke()
            }

            router.push(it)
        }
    }
}


class FinalCoordinatorsFactory
{
    //empty
}


class FinalFragmentsFactory
{
    fun makeFinalFragment() = FinalFragment.newInstance()
}