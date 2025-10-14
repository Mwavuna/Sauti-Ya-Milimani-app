package com.example.sautiyamilimani_test1.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

// 🪄 Step 1: Define the delegate
val Context.dataStore by preferencesDataStore(name = "user_prefs")
