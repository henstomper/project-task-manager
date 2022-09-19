import axios from 'axios'
import { createApp, onMounted } from 'vue'
import { createWebHistory, createRouter } from "vue-router";
import App from './App.vue'
import Users from './components/Users.vue'
import UserView from './components/UserView.vue'
import ProjectView from './components/ProjectView.vue'

const routes = [
    {path: '/', component: Users},
    {path: '/employees/:id', component: UserView},
    {path: '/project/:id', component: ProjectView}
]

const router = createRouter({
    history: createWebHistory(),
    routes,
  })

const app = createApp(App)
app.use(router)
app.mount('#app')
