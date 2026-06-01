🛒 CanBeCheaper – Product Price Tracker
> Track prices, compare stores, save money over the years.
---
📱 About
CanBeCheaper is an Android app built with Kotlin and Jetpack Compose designed to help you monitor everyday product prices. Add prices from different stores, compare them, and always know where to buy cheapest.
---
✨ Features
📝 Price logging – add product prices from any store
🔄 Unit switching – supports multiple units of measurement:
weight: `kg`, `g`, `dag`
volume: `l`, `ml`
count: `pcs`, `pack`
📈 Price history – track price changes over time (not available yet)
🔍 Product search – quickly find any saved product
---
🖼️ Screenshots
<img width="540" height="1200" alt="Screenshot_20260601_214207" src="https://github.com/user-attachments/assets/eb44a00e-52a4-45e0-abee-d40e7c110d28" />
<img width="540" height="1200" alt="Screenshot_20260601_230925" src="https://github.com/user-attachments/assets/cb0b8a00-b183-4e38-84ab-f727ab1b4582" />
<img width="540" height="1200" alt="Screenshot_20260601_230911" src="https://github.com/user-attachments/assets/3bc6fde1-bff6-4010-9d3f-4d7584cc6263" />
<img width="540" height="1200" alt="Screenshot_20260601_230855" src="https://github.com/user-attachments/assets/cfc3cc3c-d1ee-45dd-8fc1-045556dd3e95" />
<img width="540" height="1200" alt="Screenshot_20260601_230749" src="https://github.com/user-attachments/assets/789319fc-c72d-4a6c-aaf6-839c747b75b3" />

---
🛠️ Tech Stack

Kotli, Jetpack Compose, Material Design 3
MVVM	Overall architecture pattern
REST API, KTOR
Hilt, Dagger	Dependency injection
---
🏗️ Architecture
The app follows MVVM (Model-View-ViewModel) clean architecture:
```
UI (Composables)
    ↕ State / Events
ViewModel
    ↕ Use Cases
Repository
    ↕
API
