/*
 * Inventory Profiles Next
 *
 *   Copyright (c) 2023 Plamen K. Kosseff <p.kosseff@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.anti_ad.mc.ipnext.compat.integrations

import net.fabricmc.loader.api.FabricLoader
import org.anti_ad.mc.common.extensions.ifTrue
import org.anti_ad.mc.common.extensions.tryOrElse

class CarpetIntegration: IPNtoModIntegration {

    override fun init(): Boolean {
        return FabricLoader.getInstance().getModContainer("carpet").isPresent.ifTrue {
            Integrations.___getCarpetEmptyShulkersStackSize = {
                getEmptyShulkerMaxStack()
            }
        }
    }

    private fun getEmptyShulkerMaxStack(): Int {
        // 以反射存取 Carpet，避免編譯期依賴（Carpet 為可選模組）
        val rule = tryOrElse({ null }) {
            val manager = Class.forName("carpet.CarpetServer").getField("settingsManager").get(null)
            val managerType = Class.forName("carpet.api.settings.SettingsManager")
            managerType.getMethod("getCarpetRule", String::class.java).invoke(manager, "stackableShulkerBoxes")
        } ?: return 1
        val ruleType = Class.forName("carpet.api.settings.CarpetRule")
        if (ruleType.getMethod("type").invoke(rule) == String::class.java) {
            val value = ruleType.getMethod("value").invoke(rule) as String
            return when (value.lowercase()) {
                "true" -> {
                    64
                }
                "false" -> {
                    1
                }
                else -> {
                    tryOrElse ({ 1 }) {
                        value.toInt() }
                }

            }
        }
        return 1
    }
}
