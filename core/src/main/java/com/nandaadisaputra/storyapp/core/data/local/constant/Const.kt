package com.nandaadisaputra.storyapp.core.data.local.constant

class Const {
    object CONS {
        const val WAITING= "Loading...."
        const val FAVORITE_USER = "FAVORITE"
    }
    object LIKES {
        const val LIKE = "LIKE"
        const val UN_LIKE = "UN LIKE"
        const val LOADING_LIKE = "Success Add Favorites"
        const val LOADING_UNLIKE = "Success Delete Favorites"
    }
    object SESSION {
        const val BIOMETRIC = "biometric"
    }
    object BIOMETRIC {
        const val BIOMETRICAUTH = "Biometric Authentication"
        const val BIOMETRICCREDENTIALS = "Enter biometric credentials to proceed"
        const val BIOMETRICDESCRIPTION = "Input your Fingerprint or FaceID to ensure it's you"
        const val CANCEL = "Cancel"
    }

    object CONSTANTS {
        const val THEME_KEY = "theme_key"
        const val DARK_MODE_KEY = "dark_mode_key"
    }
    object TITLE {
        const val SETTING= "Setting"
        const val DETAIL= "Detail"
        const val HOME = "Home"
        const val ADD = "Add Story"
    }
}