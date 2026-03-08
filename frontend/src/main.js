import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'

// Vuetify
import 'vuetify/styles'
import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import colors from "vuetify/util/colors";

const vuetify = createVuetify({
    components,
    directives,
    theme: {
        defaultTheme: 'light',
        themes: {
            light: {
                dark: false,
                colors: {
                    primary: colors.red.darken1,
                    secondary: colors.red.lighten4,
                    background: colors.shades.white
                }
            }
        }
    }
})

createApp(App).use(vuetify).mount('#app')
