#!/bin/bash

# Script pour lancer l'application TodoApp facilement
# Utilisation: ./run-app.sh [options]


PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_DIR"

# Couleurs pour l'affichage
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}=== TodoApp Launcher ===${NC}\n"

# Vérifier si adb est disponible
if ! command -v adb &> /dev/null; then
    echo -e "${RED}❌ Erreur: adb n'est pas installé ou n'est pas dans le PATH${NC}\n"

    echo -e "${YELLOW}=== SOLUTIONS ===${NC}\n"

    echo -e "${BLUE}Option 1: Ajouter Android SDK au PATH (rapide)${NC}"
    echo -e "Exécutez une seule fois:"
    echo -e "  ${YELLOW}export PATH=\$PATH:/Users/nicolasbellina/Library/Android/sdk/platform-tools${NC}"
    echo ""

    echo -e "${BLUE}Option 2: Installation permanente du SDK${NC}"
    echo -e "Vous pouvez installer Android SDK via:"
    echo -e "  ${YELLOW}brew install android-sdk${NC}"
    echo ""

    echo -e "${BLUE}Option 3: Configurer depuis Android Studio${NC}"
    echo -e "1. Ouvrez Android Studio"
    echo -e "2. Tools → SDK Manager"
    echo -e "3. Installez 'Android SDK Platform Tools'"
    echo ""

    echo -e "${YELLOW}Après installation, relancez:${NC}"
    echo -e "  ${YELLOW}./run-app.sh${NC}"
    echo ""

    # Proposer installation automatique
    if [ -f "$(dirname "${BASH_SOURCE[0]}")/install-sdk.sh" ]; then
        echo -e "${BLUE}─────────────────────────────────────${NC}"
        read -p "Voulez-vous installer automatiquement (Homebrew)? (y/n) " -n 1 -r
        echo
        if [[ $REPLY =~ ^[Yy]$ ]]; then
            echo -e "${BLUE}Lancement du script d'installation...${NC}\n"
            bash "$(dirname "${BASH_SOURCE[0]}")/install-sdk.sh"
            exit 0
        fi
    fi

    exit 1
fi

# Vérifier les appareils connectés
DEVICES_LIST=$(adb devices | tail -n +2 | grep -v "^$")
DEVICES_COUNT=$(echo "$DEVICES_LIST" | grep -c . || true)

echo -e "${BLUE}Appareils disponibles:${NC}"
if [ "$DEVICES_COUNT" -eq 0 ]; then
    echo "Aucun appareil"
else
    echo "$DEVICES_LIST"
fi
echo ""

if [ "$DEVICES_COUNT" -eq 0 ]; then
    echo -e "${RED}❌ Aucun appareil/émulateur Android détecté!${NC}"
    echo -e "${BLUE}Options disponibles:${NC}"
    echo "1. Lancez un émulateur depuis Android Studio"
    echo "2. Connectez un appareil physique via USB"
    echo "3. Vérifiez la connexion USB de votre appareil"
    echo ""
    echo -e "${YELLOW}Après avoir connecté un appareil/émulateur, relancez:${NC}"
    echo -e "  ${YELLOW}./run-app.sh${NC}"
    exit 1
else
    echo -e "${GREEN}✓ Appareil(s) détecté(s)${NC}\n"
fi

# Options
BUILD_TYPE="debug"
INSTALL_ONLY=false
BUILD_ONLY=false

# Parser les arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        --release)
            BUILD_TYPE="release"
            shift
            ;;
        --install-only)
            INSTALL_ONLY=true
            shift
            ;;
        --build-only)
            BUILD_ONLY=true
            shift
            ;;
        --help)
            echo "Usage: ./run-app.sh [options]"
            echo ""
            echo "Options:"
            echo "  --release        Construire la version release (au lieu de debug)"
            echo "  --install-only   Installer uniquement sans construire"
            echo "  --build-only     Construire uniquement sans installer"
            echo "  --help           Afficher cette aide"
            exit 0
            ;;
        *)
            echo -e "${RED}Option inconnue: $1${NC}"
            echo "Utilisez --help pour voir les options"
            exit 1
            ;;
    esac
done

# Construire l'APK
if [ "$INSTALL_ONLY" = false ]; then
    echo -e "${BLUE}📦 Construction de la version ${BUILD_TYPE}...${NC}"
    if [ "$BUILD_TYPE" = "debug" ]; then
        ./gradlew assembleDebug || { echo -e "${RED}Erreur lors de la construction${NC}"; exit 1; }
    else
        ./gradlew assembleRelease || { echo -e "${RED}Erreur lors de la construction${NC}"; exit 1; }
    fi
    echo -e "${GREEN}✓ Construction terminée${NC}\n"
fi

# Installer et lancer
if [ "$BUILD_ONLY" = false ]; then
    DEVICES_CHECK=$(adb devices | tail -n +2 | grep -v "^$")

    if [ -z "$DEVICES_CHECK" ]; then
        echo -e "${RED}❌ Aucun appareil disponible pour l'installation${NC}"
        echo -e "${YELLOW}Veuillez connecter un appareil ou lancer un émulateur${NC}"
        exit 1
    fi

    echo -e "${BLUE}📱 Installation de l'application...${NC}"
    if [ "$BUILD_TYPE" = "debug" ]; then
        ./gradlew installDebug || { echo -e "${RED}Erreur lors de l'installation${NC}"; exit 1; }
    else
        ./gradlew installRelease || { echo -e "${RED}Erreur lors de l'installation${NC}"; exit 1; }
    fi

    echo -e "${GREEN}✓ Application installée${NC}\n"

    # Lancer l'application
    echo -e "${BLUE}🚀 Lancement de l'application...${NC}"
    PACKAGE_NAME="np.com.bimalkafle.todoapp"
    adb shell am start -n "$PACKAGE_NAME/.MainActivity"

    echo -e "${GREEN}✓ Application lancée!${NC}\n"
    echo -e "${BLUE}=== Affichage des logs en direct...${NC}"
    echo -e "${YELLOW}(Appuyez sur Ctrl+C pour arrêter l'affichage des logs)${NC}\n"

    adb logcat | grep --color=auto -E "TodoApp|${PACKAGE_NAME}|System.out|ERROR|WARN" || true
fi
