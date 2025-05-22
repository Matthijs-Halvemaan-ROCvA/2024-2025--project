<template>
  <footer class="footer">
    <div class="container">
      <p>&copy; 2024-2025 Your Company. All rights reserved.</p>
      <p v-if="greeting">{{ greeting }}</p>
      <p v-else>Loading greeting...</p>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'TheFooter',
};
</script>
<script setup>
import { ref, onMounted } from 'vue';

const apiUrl = import.meta.env.VITE_API_URL;
const backendUrl = `${apiUrl}/demo/greet`;

console.log('Backend URL:', backendUrl);

const greeting = ref('');
const fetchGreeting = async () => {
  try {
    const response = await fetch(backendUrl);
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    // const data = await response;
    greeting.value = await response.text();
  } catch (error) {
    console.error('Error fetching greeting:', error);
  }
};

onMounted(() => {
  fetchGreeting();
});
</script>

<style scoped>
.footer {
  background-color: #333;
  color: #fff;
  text-align: center;
  padding: 1rem 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
}
</style>
