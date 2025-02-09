import { defineConfig } from 'vite';

export default defineConfig({
  // Add your custom Vite configuration here
  build: {
    rollupOptions: {
      input: 'src/main/resources/META-INF/resources/frontend/index.html', // Adjust the path if needed
    },
  },
});
