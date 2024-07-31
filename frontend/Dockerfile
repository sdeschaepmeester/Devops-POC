# Étape 1 : Construire l'application
FROM node:18 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers package.json et package-lock.json (ou yarn.lock)
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Copier le reste des fichiers de l'application
COPY . .

# Construire l'application pour la production
RUN npm run build

# Étape 2 : Servir l'application avec un serveur Node.js
FROM node:18

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers de construction depuis l'étape précédente
COPY --from=build /app/build /app/build

# Installer un serveur statique pour Node.js
RUN npm install -g serve

# Exposer le port 3000 (ou tout autre port que vous souhaitez utiliser)
EXPOSE 3000

# Démarrer le serveur statique
CMD ["serve", "-s", "build", "-l", "3000"]