import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { OhVueIcon, addIcons } from "oh-vue-icons";
import { FaRegularEye, FaRegularEyeSlash } from "oh-vue-icons/icons";
import { createPinia } from 'pinia';
import { createApp } from 'vue';
import './assets/main.css';

addIcons(FaRegularEyeSlash, FaRegularEye )

import App from './App.vue';
import router from './router';

const app = createApp(App)

app.component("v-icon", OhVueIcon);
app.use(createPinia())
app.use(router)

app.mount('#app')
