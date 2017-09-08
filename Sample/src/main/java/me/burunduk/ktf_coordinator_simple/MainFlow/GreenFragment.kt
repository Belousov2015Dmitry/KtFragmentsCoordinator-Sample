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


enum class GreenResult { Back, Done }


class GreenFragment : LifecycleFragment(), FlowFragment<GreenResult>
{
    override var completionHandler: FlowFragmentHandler<GreenResult>? = null


    companion object {
        fun newInstance() = GreenFragment()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater!!.inflate(R.layout.fragment_green, container, false).apply {

                findViewById<Button>(R.id.fragment_green_back).setOnClickListener {
                    completionHandler?.invoke(GreenResult.Back)
                }

                findViewById<Button>(R.id.fragment_green_done).setOnClickListener {
                    completionHandler?.invoke(GreenResult.Done)
                }
            }
}