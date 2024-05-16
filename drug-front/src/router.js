import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./views/Profile.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      // lazy-loaded
      component: () => import('./views/BoardAdmin.vue')
    },
    {
      path: '/user',
      name: 'user',
      // lazy-loaded
      component: () => import('./views/BoardUser.vue')
    },
    {
      path: '/drug-create-edit',
      name: 'drug-create',
      component: () => import('./views/DrugCreateEdit.vue')
    },
    {
      path: '/drug-edit/:id',
      name: 'drug-edit',
      component: () => import('./views/DrugCreateEdit.vue'),
      props: true
    },
    {
      path: '/prescription-create',
      name: 'prescription-create',
      component: () => import('./views/PrescriptionCreateEdit.vue')
    },
    {
      path: '/prescriptions',
      name: 'prescriptions',
      component: () => import('./views/PrescriptionList.vue')
    },
    {
      path: '/prescription-edit/:id',
      name: 'prescription-edit',
      component: () => import('./views/PrescriptionCreateEdit.vue'),
      props: true
    }
  ]
});

// router.beforeEach((to, from, next) => {
//   const publicPages = ['/login', '/register', '/home'];
//   const authRequired = !publicPages.includes(to.path);
//   const loggedIn = localStorage.getItem('user');

//   // trying to access a restricted page + not logged in
//   // redirect to login page
//   if (authRequired && !loggedIn) {
//     next('/login');
//   } else {
//     next();
//   }
// });
