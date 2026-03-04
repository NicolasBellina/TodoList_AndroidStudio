# TodoApp - Jetpack Compose

Une application Android moderne de gestion de tâches développée avec **Jetpack Compose** et **Kotlin**.

## 📋 Fonctionnalités

- ✅ **Afficher** la liste complète des tâches
- ✅ **Ajouter** de nouvelles tâches avec timestamp
- ✏️ **Modifier** le titre d'une tâche existante
- ❌ **Supprimer** une tâche par ID
- 📅 **Timestamps** automatiques pour chaque tâche

## 🏗️ Architecture - Structure du Projet

L'application suit une architecture **MVVM** (Model-View-ViewModel) bien organisée :

```
app/src/main/java/np/com/bimalkafle/todoapp/
├── MainActivity.kt                          # Point d'entrée de l'application
├── data/
│   ├── model/
│   │   └── Todo.kt                          # Modèle de données (data class)
│   └── manager/
│       └── TodoManager.kt                   # Gestion CRUD (singleton)
└── ui/
    ├── screens/
    │   └── TodoListPage.kt                  # Interface utilisateur Compose
    ├── viewmodel/
    │   └── TodoViewModel.kt                 # Logique métier & gestion d'état
    └── theme/
        ├── Color.kt, Theme.kt, Type.kt      # Thème Material Design 3
```

## 🛠️ Technologies utilisées

- **Kotlin** - Langage de programmation principal
- **Jetpack Compose** - Framework UI déclaratif
- **AndroidX Lifecycle** - ViewModel et LiveData pour la gestion d'état
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

## 💻 Utilisation programmatique

### TodoManager - CRUD operations

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

### TodoViewModel - Avec LiveData

```kotlin
val viewModel: TodoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

// Observer les changements
viewModel.todoList.observe(this) { todos ->
    // Mettre à jour l'UI
}

// Actions utilisateur
viewModel.addTodo("Nouvelle tâche")
viewModel.updateTodo(1, "Titre modifié")
viewModel.deleteTodo(1)
```

## 📝 Modèle de données

```kotlin
data class Todo(
    var id: Int,              // ID unique (généré automatiquement)
    var title: String,        // Titre de la tâche
    var createdAt: Date       // Date de création
)
```

## ✨ Fonctionnalités détaillées

### Ajouter une tâche
- Saisir le titre dans le champ texte
- Cliquer sur le bouton "Add"
- La tâche est ajoutée en haut de la liste avec un timestamp

### Modifier une tâche
- Cliquer sur l'icône ✏️ (crayon) d'une tâche
- Une dialog s'affiche avec le titre actuel
- Modifier le texte et cliquer "Sauvegarder"

### Supprimer une tâche
- Cliquer sur l'icône 🗑️ (corbeille) d'une tâche
- La tâche est supprimée immédiatement

## 👤 Auteur

Nicolas Bellina - ESGI M1

## 📅 Dernière mise à jour

4 mars 2026
