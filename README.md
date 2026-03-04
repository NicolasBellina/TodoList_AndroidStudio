# TodoApp - Android (Jetpack Compose)

Bienvenue sur le projet **TodoApp**. Il s'agit d'une application Android moderne de gestion de tâches (To-Do List) développée en **Kotlin** avec le framework **Jetpack Compose**.

## 📋 Fonctionnalités

*   **Ajouter une tâche** : Saisissez un texte et ajoutez-le à votre liste.
*   **Modifier une tâche** : Cliquez sur l'icône "crayon" pour modifier une tâche existante. Le texte actuel est pré-rempli pour faciliter l'édition.
*   **Supprimer une tâche** : Cliquez sur l'icône "poubelle" pour retirer une tâche de la liste.
*   **Affichage** : Liste défilante avec date et heure de création pour chaque tâche, présentée de manière claire et organisée.

---

## 🚀 Comment lancer le projet

Vous avez plusieurs options pour lancer l'application.

### Option 1 : Via le script facilité (Recommandé)

Un script `run-app.sh` est inclus à la racine pour automatiser la compilation et l'installation.

1.  Ouvrez un terminal à la racine du projet.
2.  Assurez-vous d'avoir un émulateur Android lancé ou un appareil physique connecté.
3.  Exécutez la commande suivante :
    ```bash
    ./run-app.sh
    ```
    *Ce script se charge de compiler l'application, de l'installer sur l'appareil connecté et de la lancer automatiquement.*

### Option 2 : Via Android Studio

1.  Ouvrez **Android Studio**.
2.  Sélectionnez **Open** et choisissez le dossier `TodoList_AndroidStudio`.
3.  Attendez que l'indexation et la synchronisation Gradle soient terminées.
4.  Sélectionnez un émulateur ou un appareil dans la barre d'outils.
5.  Cliquez sur le bouton **Run** (le triangle vert ▶️).

### Option 3 : Via la ligne de commande (Gradle)

Si vous préférez utiliser Gradle directement :

1.  **Compiler le projet :**
    ```bash
    ./gradlew assembleDebug
    ```
2.  **Installer sur l'appareil :**
    ```bash
    ./gradlew installDebug
    ```
3.  **Lancer l'application (via ADB) :**
    ```bash
    adb shell am start -n np.com.bimalkafle.todoapp/.MainActivity
    ```

---

## 🏗️ Architecture du Code

Le projet suit l'architecture **MVVM (Model-View-ViewModel)** pour une séparation claire des responsabilités.

Les fichiers sources se trouvent désormais dans le répertoire standard :
`app/src/main/java/np/com/bimalkafle/todoapp/`

*   **`model/Todo.kt`** : La classe de données représentant une tâche.
*   **`viewmodel/TodoViewModel.kt`** : Gère la logique métier et l'état de la liste des tâches.
*   **`ui/screens/TodoListPage.kt`** : L'écran principal contenant la liste et les formulaires (Interface Utilisateur).
*   **`MainActivity.kt`** : Le point d'entrée de l'application.

---

## 🛠️ Prérequis techniques

*   **Android Studio** (Hedgehog ou plus récent recommandé)
*   **JDK 17** (utilisé par Gradle)
*   **Android SDK** (API 34 configurée dans le projet)
