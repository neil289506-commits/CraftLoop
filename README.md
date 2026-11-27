# CraftLoop

Minecraft（Fabric 26.x）客戶端模組：**連續合成**與**一鍵移動物品**。

基於 [Inventory Profiles Next](https://codeberg.org/Inventory-Profiles-Next-Mod/IPN)（IPN）修改，移除了背包排序功能。依 AGPL-3.0 授權散佈，保留原作者的版權聲明。

## 功能
- **連續合成**：合成後自動從背包補料；按住 Shift＋Alt 可持續自動合成。支援工作台與切石機。
- **一鍵移動物品**：移動相符／全部物品，並支援捲輪移動。
- **修飾鍵**：Alt（含快捷列）、Shift（全部）、Ctrl（只移動指到的物品）、Caps Lock（只補滿既有堆疊）。

快捷鍵與連續合成的完整說明見 [docs/連續合成與快捷鍵.md](docs/連續合成與快捷鍵.md)。

## 相依
- Minecraft 26.1.2／26.2／26.3、Fabric Loader、Fabric API、Fabric Language Kotlin
- libIPN 6.9.0

## 建置
```
./gradlew :platforms:fabric-26.2:build
```
成品在 `platforms/fabric-26.2/build/libs/`。也可使用 `.github/workflows/build.yml` 由 GitHub Actions 建置。

## 與原版的差異
- 模組 ID 改為 `craftloop`，可與原版同時安裝而不衝突；設定資料夾為 `config/craftloop`。
- 移除：排序按鈕、排序熱鍵、排序規則設定。
- 原版的 README 保留在 [docs/README-IPN-original.md](docs/README-IPN-original.md)。

## 授權
AGPL-3.0，見 [LICENSE](LICENSE)。
