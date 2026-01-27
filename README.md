# TodoApp - Jetpack Compose
Une application Android simple de gestion de tâches built with Jetpack Compose.
## Fonctionnalités
- ✅ **Ajouter** des tâches
- ✏️ **Modifier** des tâches
- ❌ **Supprimer** des tâches
- 📋 **Afficher** la liste des tâches avec timestamps
## Architecture
cd "/Users/nicolasbellina/Documents/ESGI M1/android studio/JetpackCompose_Playground/3_TodoApp" && find . -maxdepth 1 -type f | sort- **Todo.kt** - Modèle de données
- **TodoManager.kt** - Gestion des données (singleton)
- **TodoViewModel.kt** - ViewModel pour la logique métier
- **TodoListPage.kt** - Interface utilisateur Compose
- **MainActivity.kt** - Point d'entrée de l'application
## Technologie
- Jetpack Compose pour l'UI
- AndroidX Lifecycle pour le ViewModel
- Material Design 3
## Lancer l'application
```bash
bash run-app.sh
```
## Prérequis
- Android SDK configuré
- Android Emulator ou appareil physique connecté
# TodoList_AndroidStudio
