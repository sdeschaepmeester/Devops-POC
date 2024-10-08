describe('Get to the connection page and login', () => {
  it('Visits the app and logs in', () => {
    //
    // TESTING THE CONNECTION
    //
    // Visit first page of application
    cy.visit('http://localhost:3000');

    // Verify we're on the Connection form
    cy.contains('Connexion');

    // Enter the username
    cy.get('input[name="username"]').type('andrew');

    // Enter the password
    cy.get('input[name="password"]').type('andrew');

    // Click on connection
    cy.get('button[type="submit"]').click();

    // We're redirected on dashboard after connection
    cy.url().should('include', '/dashboard');
    cy.contains('Medhead').should('be.visible');  // This text is in the header

    //
    // TESTING THE SEARCH HOSPITAL FORM
    //
    // Click on find address input

    cy.get('input[name="enterAddress"]').type('2 rue Louis Armand Eaubonne'); 

    cy.get('button[id="findAddressBtn"]').click();

    // Wait for suggestions to show
    cy.get('ul').should('be.visible');

    // Click on first suggestion
    cy.get('li').eq(0).click();

    // Open select to choose a speciality
    cy.get('#selectSpecialityBtn').click();

    // Scroll to option if needed
    cy.contains('Pathology group - Diagnostic neuropathology')
      .scrollIntoView()  
      .should('be.visible') 
      .click({ force: true });



    // Select option
    cy.contains('Pathology group - Diagnostic neuropathology').click();

    // Click on body to be sure the specialities select isnt shown
    cy.get('body').click(0, 0);

    cy.get('button[id="searchBtn"]').click(); // Click on search

    cy.contains('Liste des hôpitaux proches correspondants à votre recherche :');

    cy.get('div[id="hospitalCardContainer"]')
      .should('be.visible');

    // Select first hospital result
    cy.get('div[id="hospitalCardContainer"] .MuiCard-root').eq(0).click(); 
    
    cy.get('button[id="reserveBedBtn"]').click(); // Click on search

    cy.contains("Le lit a bien été réservé. Vous allez être redirigé vers la page d'accueil.").should('be.visible'); 
  });
});