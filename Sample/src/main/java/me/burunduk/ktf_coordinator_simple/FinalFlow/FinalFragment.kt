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

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import me.burunduk.ktf_coordinator.FlowFragment
import me.burunduk.ktf_coordinator.FlowFragmentHandler
import me.burunduk.ktf_coordinator_simple.R

/**
 * Created by Dmitry Belousov on 07.09.17.
 */


enum class FinalResult { Exit }


class FinalFragment : LifecycleFragment(), FlowFragment<FinalResult>
{
    override var completionHandler: FlowFragmentHandler<FinalResult>? = null


    companion object {
        fun newInstance() = FinalFragment()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater!!.inflate(R.layout.fragment_final, container, false).apply {

                findViewById<Button>(R.id.fragment_final_exit).setOnClickListener {
                    completionHandler?.invoke(FinalResult.Exit)
                }
            }
}