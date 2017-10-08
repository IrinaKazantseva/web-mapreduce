import Vue from 'vue'
import Router from 'vue-router'
import Calculate from '@/components/calculate/vue/Calculate'
import StressTest from '@/components/stress-test/vue/StressTest'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Calculate',
      component: Calculate
    },
    {
      path: '/stress-test',
      name: 'StressTest',
      component: StressTest
    }
  ]
})
