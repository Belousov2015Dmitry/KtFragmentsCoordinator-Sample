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

package me.burunduk.ktf_coordinator

/**
 * Created by Dmitry Belousov on 28.08.2017.
 */


abstract class BaseCoordinator<CoordinatorsFactory, FragmentsFactory> : Coordinatable
{
    abstract var coordinatorsFactory: CoordinatorsFactory
    abstract var fragmentsFactory: FragmentsFactory

    protected abstract val childCoordinators: MutableSet<Coordinatable>


    fun addChild(coordinator: Coordinatable) {
        childCoordinators.add(coordinator)
    }

    fun removeChild(coordinator: Coordinatable) {
        childCoordinators.remove(coordinator)
    }
}