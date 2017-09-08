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

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import java.lang.ref.WeakReference

/**
 * Created by Dmitry Belousov on 29.08.2017.
 */


open class BaseRouter(
        override var fragmentManager: WeakReference<FragmentManager>,
        override var containerID: Int
) : Routable
{
    override fun push(fragment: Fragment) {
        fragmentManager.get()?.let { fm ->
            fm.beginTransaction()
                    .replace(containerID, fragment)
                    .addToBackStack(fragment.tag)
                    .commit()
        }
    }

    override fun pop() {
        fragmentManager.get()
                ?.popBackStack()
    }

    override fun popTo(fragment: Fragment) {
        fragmentManager.get()
                ?.popBackStack(fragment.tag, 0)
    }
}