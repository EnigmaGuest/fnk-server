// 渲染icon
import EgIcon from "@/components/common/eg-icon.vue";
import {h} from "vue";

export const renderIcon = (icon?: string, localIcon?: string) => {
    if (!icon && !localIcon) {
        console.warn('not find icon')
        return null
    }
    // 默认p 内容为icon
    return () => h(EgIcon, {icon, localIcon})
}
