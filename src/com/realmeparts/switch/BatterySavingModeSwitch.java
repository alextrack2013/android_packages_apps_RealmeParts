/*
 * Copyright (C) 2024 The LineageOS Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.realmeparts;

import android.content.Context;

import android.os.SystemProperties;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;

public class BatterySavingModeSwitch implements OnPreferenceChangeListener {

    private static Context mContext;

    public BatterySavingModeSwitch(Context context) {
        mContext = context;
    }

    public static boolean isCurrentlyEnabled(Context context) {
        String perfProfile = SystemProperties.get("perf_profile");
        if (perfProfile.equals("2")) {return true;} else {return false;}
    }

    public static void ShowToast() {
        if (isCurrentlyEnabled(mContext)) {
            Toast.makeText(mContext, "Battery Saver is activated. ", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(mContext, "Battery Saver is deactivated. ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Boolean enabled = (Boolean) newValue;
        DeviceSettings.mGameModeSwitch.setEnabled(!enabled);
        SystemProperties.set("perf_profile", enabled ? "2" : "0");
        ShowToast();
        return true;
    }
}
