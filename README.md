# TodoApp - Jetpack Compose

Une application Android moderne de gestion de tâches développée avec **Jetpack Compose** et **Kotlin**.

## 📋 Fonctionnalités

- ✅ **Afficher** la liste complète des tâches
- ✅ **Ajouter** de nouvelles tâches avec timestamp
- ✏️ **Modifier** le titre d'une tâche existante
- ❌ **Supprimer** une tâche par ID
- 📅 **Timestamps** automatiques pour chaque tâche

## 🏗️ Architecture

L'application suit une architecture **MVVM** (Model-View-ViewModel) :

| Fichier | Description |
|---------|-------------|
| **Todo.kt** | Modèle de données - classe data pour une tâche |
| **TodoManager.kt** | Gestion des données - singleton pour les opérations CRUD |
| **TodoViewModel.kt** | ViewModel - logique métier et gestion d'état |
| **TodoListPage.kt** | Interface utilisateur Compose |
| **MainActivity.kt** | Point d'entrée de l'application |

## 🛠️ Technologies utilisées

- **Kotlin** - Langage de programmation
- **Jetpack Compose** - Framework UI déclaratif
- **AndroidX Lifecycle** - ViewModel et LiveData
- **Material Design 3** - Design System
- **Gradle** - Gestion des dépendances et build

## 🚀 Lancer l'application

### ⚠️ Étape 1 : Lancer un émulateur Android (OBLIGATOIRE)

Avant de lancer l'app, tu **DOIS** d'abord lancer un émulateur. Voici comment :

#### Depuis Android Studio
1. Ouvrir **Android Studio**
2. Cliquer sur **Device Manager** (icône téléphone en bas à droite)
3. Dans la liste des appareils, cliquer sur le bouton ▶️ **Play** pour lancer un émulateur
4. **Attendre que l'émulateur démarre complètement** (cela peut prendre 30-60 secondes)

#### Vérifier que l'émulateur est connecté
```bash
adb devices
```
Tu devrais voir quelque chose comme :
```
List of attached devices
emulator-5554          device
```

### ⚠️ Étape 2 : Installer et lancer l'app

Une fois l'émulateur lancé et connecté, tu peux installer l'app.

#### Via Android Studio (Recommandé ✅)
1. Cliquer sur **Build** → **Make Project** (ou ⌘B)
2. Cliquer sur **Run** (ou ⌃R) 
3. L'app se lance automatiquement sur l'émulateur

#### Via le terminal avec Gradle
```bash
cd "/Users/nicolasbellina/Documents/ESGI M1/android studio/JetpackCompose_Playground/TodoApp"
./gradlew installDebug
```

### 🔍 Consulter les logs
```bash
adb logcat | grep TodoApp
```

### 🆘 Troubleshooting
**Erreur : "No connected devices!"**
- ✅ Assurez-vous qu'un émulateur est lancé et visible dans `adb devices`
- ✅ Si aucun émulateur n'apparaît, relancez-le depuis Android Studio → Device Manager

## 📦 Prérequis

- Android SDK 34+ configuré
- Android Emulator ou appareil physique connecté
- Java 11+
- Gradle 8.0+

## 💻 Utilisation de TodoManager

```kotlin
// Récupérer toutes les tâches
val todos: List<Todo> = TodoManager.getAllTodo()

// Ajouter une tâche
TodoManager.addTodo("Ma première tâche")

// Mettre à jour une tâche
TodoManager.updateTodo(id = 1, newTitle = "Tâche modifiée")

// Supprimer une tâche
TodoManager.deleteTodo(id = 1)
```

## 📝 Modèle de données

```kotlin
data class Todo(
    val id: Int,
    var title: String,
    val createdAt: Date
)
```