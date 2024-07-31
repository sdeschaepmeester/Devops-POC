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

# Étape 2 : Servir l'application avec serve
FROM node:18

# Installer `serve` pour servir l'application
RUN npm install -g serve

# Copier les fichiers buildés depuis l'étape précédente
COPY --from=build /app/build /app/build

# Définir le répertoire de travail
WORKDIR /app

# Exposer le port que l'application utilisera
EXPOSE $PORT

# Démarrer l'application avec `serve`
CMD ["sh", "-c", "serve -s build -l $PORT"]
