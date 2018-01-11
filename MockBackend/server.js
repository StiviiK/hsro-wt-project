const express = require('express')
const app = express()
const bodyParser = require('body-parser')

app.use(bodyParser.json());
app.use((req, res, next) => {
    res.header("Access-Control-Allow-Headers", "*");
    res.header("Access-Control-Allow-Origin", "*");
    next();
})

app.post('/jwt/get', (req, res) => {
    res.send(JSON.stringify({
        token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoxMDY2MjM5Mjk1MzE1OTg3MDAwMDAsImdvb2dsZVRva2VuIjoiZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SjFjMlZ5SWpveE5Td2laMjl2WjJ4bFZHOXJaVzRpT2lKRFJUUTNPRGMyTkVZeVFUTkJORVF3UmtFeVJFUXdRVUpCUWpkQk1qRTRPVFUwTmpaQk4wVTJNVVpCUVVVd016UXhSRVl4TWpBNE9FVkRNa0k0TTBJM0luMC5fbW5BOU1wckxRY1owdE90M18tZDVvY3M5UmVUSUdMa0Y5X2RwM0ZLNmowIn0.f3gq86UTYR_M0HlW3pFtW7EMliIua59nt6qSrbM3-zM',
        username: 'none',
        id: 5,
    }))
})

app.post('/api/logout', (req, res) => {
    res.send({ status: 'success' })
})

app.listen(3000, () => console.log('Mockbackend listening on port 3000!'))