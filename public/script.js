function calcPrice() {
    // Releavant input data
    let pickup = document.getElementById("pickup")
    let delivery = document.getElementById("delivery")
    let vehicle = document.getElementById("vehicle")

    let breakOut = false

    // Check if any required fields are blank, and alert the user
    for (let item of [pickup, delivery, vehicle]) {
        item.style.borderColor = "black";
        if (item.value == "") {
            item.style.borderColor = "red";
            breakOut = true
        }
    }

    if (breakOut) { return }

    // Contents of POST request
    let data = {
        "pickupPostcode": pickup.value,
        "deliveryPostcode": delivery.value,
        "vehicle": vehicle.value
    };

    // Replace help text with loading symbol
    document.getElementById("text").innerHTML = ""
    document.getElementById("dots").style.display = "block";

    (async () => {
        const rawResponse = await fetch('/quote', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        const content = await rawResponse.json();
        let v = vehicle.options[vehicle.selectedIndex].text
        updateHTML(content, v)
    })();
}


function updateHTML(content, v) {
    document.getElementById("dots").style.display = "none";
    document.getElementById("text").innerHTML = `A delivery from ${content.pickupPostcode} to ${content.deliveryPostcode} using a ${v} will cost you:`
    document.getElementById("price").innerHTML = `£${content.price}`
}