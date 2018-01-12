const express = require('express')
const app = express()
const bodyParser = require('body-parser')

app.use(bodyParser.json());
app.use((req, res, next) => {
    res.header("Access-Control-Allow-Headers", "*");
    res.header("Access-Control-Allow-Origin", "*");
    next();
})

app.post('/api/jwt/get', (req, res) => {
    res.send({
        status: true,
        message: "successfully generated jwt!",
        data: {
            token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoxMDY2MjM5Mjk1MzE1OTg3MDAwMDAsImdvb2dsZVRva2VuIjoiZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SjFjMlZ5SWpveE5Td2laMjl2WjJ4bFZHOXJaVzRpT2lKRFJUUTNPRGMyTkVZeVFUTkJORVF3UmtFeVJFUXdRVUpCUWpkQk1qRTRPVFUwTmpaQk4wVTJNVVpCUVVVd016UXhSRVl4TWpBNE9FVkRNa0k0TTBJM0luMC5fbW5BOU1wckxRY1owdE90M18tZDVvY3M5UmVUSUdMa0Y5X2RwM0ZLNmowIn0.f3gq86UTYR_M0HlW3pFtW7EMliIua59nt6qSrbM3-zM',
            user: {
                id: 5
            }
        }
    })
})

app.post('/api/logout', (req, res) => {
    console.log(req.headers);

    res.send({
        status: true,
        message: "successfully logged out!"
    })
})

app.get('/api/thread/:id', (req, res) => {
    res.send({
        status: true,
        message: "successfully loaded thread!",
        data: {
            id: req.params.id,
            views: 0,
            creator: 1,
            topic: 'Angular 4 is not working',
            question: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Hendrerit gravida rutrum quisque non tellus orci ac auctor augue. Donec ac odio tempor orci. Lacus sed viverra tellus in hac habitasse platea dictumst vestibulum. Adipiscing enim eu turpis egestas pretium aenean pharetra magna ac.',
            answers: {},
            lastUpdate: Date.now(),
        }
    })
})

app.get('/api/user/:id', (req, res) => {
    res.send({
        status: true,
        message: "user found!",
        data: {
            id: req.params.id,
            name: 'StiviK',
            email: 'fuck',
            avatar_url: 'you!'
        }
    })
})

app.listen(3000, () => console.log('Mockbackend listening on port 3000!'))