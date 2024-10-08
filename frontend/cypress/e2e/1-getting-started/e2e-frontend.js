describe('My First E2E Test', () => {
    it('Visits the app and checks for a component', () => {
        cy.visit('http://localhost:3000');  // Adjust this to your frontend URL
        cy.contains('Connexion'); // Example of checking for text in the app
    });
});