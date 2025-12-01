// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2025-11-14',

  // Client Side Rendering 설정
  ssr: false,

  devtools: { enabled: true },

  modules: ['@pinia/nuxt', '@nuxt/eslint', 'pinia-plugin-persistedstate/nuxt'],

  css: ['vuetify/styles'],

  build: {
    transpile: ['vuetify'],
  },

  vite: {
    ssr: {
      noExternal: ['vuetify'],
    },
  },

  typescript: {
    typeCheck: true,
    strict: true,
  },

  app: {
    head: {
      title: 'CareerBuilder',
      htmlAttrs: {
        lang: 'ko',
      },
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
        { name: 'description', content: 'AI 기반 커리어 빌더 서비스' },
      ],
    },
  },
});
