# 项目说明
比较简洁的vue3+ts+naive-ui+unocss的后台管理系统模板,适合再次开发。项目只有简单的页面，没有复杂的业务逻辑。当前项目还在开发中，后续会持续更新。
## 版本更新
- 1.0.0
  - 初始版本
- 2.0.0
  - 布局优化
  - 主题新增两种布局模式
    - 基本布局
    - 分离式卡片布局
  - 完善主题配置
  - 优化动态标签页
  - 优化动画效果
  - 默认主体滚动效果
  - 新增初始化加载动画


## 项目特点
- 使用pnpm管理依赖
- 使用vite构建项目
- 使用unocss进行样式管理
- 使用naive-ui组件库管理
- 使用vue-router进行路由管理
- 使用pinia进行状态管理,且实现加密持久化
- svg图标使用[Iconify](https://icones.js.org/collection/all)进行管理
- 可显示本地svg图标且可动态修改颜色
- 动态标签页可左右拖动以及右键菜单
- 自定义主题
- 部分组件（表单，列表）抽取
## 项目结构
```bash
├── README.md
├── package.json
├── 待添加
```
## 项目依赖
- [vue](https://cn.vuejs.org/)
- 待添加
## 项目截图
![img.png](doc%2Fimage%2Fimg.png)
![img_1.png](doc%2Fimage%2Fimg_1.png)
![img_2.png](doc%2Fimage%2Fimg_2.png)
## 项目运行
```bash
# 安装依赖
pnpm i
# 启动项目
pnpm run dev
# 打包项目
pnpm run build
```
# Tips
## svg动态颜色
需要把下载的svg填充的颜色数值替换成 currentColor 即可 fill="currentColor"


## 项目未完成
- [ ] 常用组件
## 持续更新中到达一个稳定版本
