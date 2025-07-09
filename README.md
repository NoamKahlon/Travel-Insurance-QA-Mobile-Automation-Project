# 📌 Project Title: Mobile Insurance Automation Testing

## 📝 Description
Automated **mobile UI testing project** for verifying insurance flows on the [Bituach Haklai website](https://www.bth.co.il) via **Android mobile browser (Chrome)**, using **Appium**, **Selenium WebDriver**, **TestNG**, and **Allure Reports**.

The project includes test flows for:

- ✅ Checking if a car has valid insurance (with real Android device)
- 🧾 Filling out travel insurance forms using WebView in Chrome
- ❗ Validating success and error messages
- 📸 Capturing screenshots on test failures
- 🔄 Switching between WebView and Native contexts

---

## 🧰 Tech Stack

- Java 17
- Maven
- Appium Java Client (9.1.0)
- Selenium WebDriver (4.33.0)
- TestNG (7.11.0)
- Allure Reports (2.13.9)
- IDE: IntelliJ IDEA

---

## 📱 Devices & Platforms

- Android 11+
- Tested with: Xiaomi / Samsung (real device)
- Chrome for Android (version 138+)

---

## 📁 Project Structure

```
MobileInsuranceProject/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/           # Page Object classes (WebView locators)
│   │       ├── flows/           # Reusable flow logic
│   │       └── utils/           # Logger, context switcher, helpers
│   └── test/
│       └── java/
│           └── tests/           # TestNG mobile test classes
├── pom.xml                      # Maven project file
└── README.md
```

---

## ▶️ How to Run

### 📦 Prerequisites

- Java 17+
- Maven installed (`mvn -v`)
- Android SDK and ADB tools
- Appium server (via NPM or Appium Desktop)
- Chrome installed on the Android device
- USB debugging enabled
- Allure CLI installed (optional)

---

## 🧪 Run Tests and Generate Allure Report

```bash
mvn clean test ; mvn allure:report
