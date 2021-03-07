### README.md content

1- Explain how you ensure user is the right one starting the app
2- How do you securely save user's data on your phone ?
3- How did you hide the API url ?
4- Screenshots of your application 

1-I put an identification page based on an API call
The user must know his id/lastname in order to access accounts
2-I have decided to save the minimum of information about the user
I do an API call and iprint it in text view
3-I haven't managed to hide the url
4-See jgp

### Requirements
- This application must be available offline.
- A refresh button allows the user to update its accounts.
- Access to the application is restricted 
- Exchanges with API must be secure ( with TLS)

My app validates all requirements exept TLS protocole in order to communicate wuth the API 
I don't know how to createt a certificate in order to use the TLS protocol

