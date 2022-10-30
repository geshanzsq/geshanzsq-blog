<template>
  <div class="footer">
    <span
      >Copyright &copy; 2020 - 2022 <a href="/"> {{ title }} </a>保留所有权利
      <a href="http://beian.miit.gov.cn/" target="_blank"> {{ recordTitle }}</a>
    </span>
  </div>
</template>
<script setup>
import { title, recordTitle } from '@/config/setting.config'
</script>
<style lang="scss" scoped>
.footer {
  text-align: center;
  font-size: 14px;
  color: #a4a4a4;
  background-color: #171c3e;
  padding: 20px;
}

.footer a {
  text-decoration-line: none;
  color: #a4a4a4;
}
</style>
