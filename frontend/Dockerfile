# Utilise l'image Node.js comme base
FROM node:18

# Définis le répertoire de travail à l'intérieur du conteneur
WORKDIR /app

# Copie les fichiers nécessaires dans le conteneur
COPY package.json package-lock.json /app/
COPY . /app/

# Installe les dépendances
RUN npm install

# Expose le port 3000 pour que l'application puisse être accessible
EXPOSE 3000

# Commande par défaut pour démarrer l'application
CMD ["npm", "start"]
