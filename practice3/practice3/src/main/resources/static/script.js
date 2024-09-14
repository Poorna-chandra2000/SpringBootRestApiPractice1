
function getPersonDetails() {
    const personId = document.getElementById('personId').value;
    const detailsContainer = document.getElementById('personDetails');

    detailsContainer.innerHTML = '';

    if (personId) {
        fetch(`/person1/${personId}`)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(error => {
                        throw new Error(error.message || 'Person not found');
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log(data); // Check the response structure

                // Ensure 'id' is present in the response
                if (data && data.id !== undefined && data.name && data.age !== undefined) {
                    const detailsHtml = `
                        <p><strong>ID:</strong> ${data.id}</p>
                        <p><strong>Name:</strong> ${data.name}</p>
                        <p><strong>Age:</strong> ${data.age}</p>
                    `;
                    detailsContainer.innerHTML = detailsHtml;
                } else {
                    detailsContainer.innerHTML = '<p>Person details are incomplete or undefined.</p>';
                }
            })
            .catch(error => {
                detailsContainer.innerHTML = `<p>Error: ${error.message}</p>`;
            });
    } else {
        detailsContainer.innerHTML = '<p>Please enter a valid Person ID.</p>';
    }
}

function addPerson() {
    const name = document.getElementById('newName').value;
    const age = document.getElementById('newAge').value;
    const detailsContainer = document.getElementById('personDetails');

    if (name && age) {
        fetch('/person1', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, age })
        })
            .then(response => response.json())
            .then(data => {
                if (data.id !== undefined && data.name && data.age !== undefined) {
                    detailsContainer.innerHTML = `
                        <p><strong>ID:</strong> ${data.id}</p>
                        <p><strong>Name:</strong> ${data.name}</p>
                        <p><strong>Age:</strong> ${data.age}</p>
                    `;
                } else {
                    detailsContainer.innerHTML = '<p>Person could not be added. Please check the input data.</p>';
                }
            })
            .catch(error => {
                detailsContainer.innerHTML = `<p>Error: ${error.message}</p>`;
            });
    } else {
        detailsContainer.innerHTML = '<p>Please enter a name and age.</p>';
    }
}
