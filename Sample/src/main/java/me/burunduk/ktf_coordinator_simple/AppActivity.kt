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

package me.burunduk.ktf_coordinator_simple

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.view.WindowManager
import me.burunduk.ktf_coordinator_simple.MainFlow.MainCoordinator
import me.burunduk.ktf_coordinator_simple.MainFlow.MainCoordinatorsFactory
import me.burunduk.ktf_coordinator_simple.MainFlow.MainFragmentsFactory

/**
 * Created by Dmitry Belousov on 07.09.17.
 */



class AppActivity : LifecycleActivity()
{
    private val coordinator by lazy {
        MainCoordinator(
                router = CustomRoute(
                        supportFragmentManager.weak(),
                        R.id.activity_app_container
                ),
                coordinatorsFactory = MainCoordinatorsFactory(),
                fragmentsFactory = MainFragmentsFactory()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_app)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        coordinator.start()
    }

    override fun onBackPressed() {}
}