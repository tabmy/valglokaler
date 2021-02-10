async function showLocales() {
    let query = window.location.search

    const localesList = await fetch(`/api/locales${query}`)
        .then(response => {
            return response.json()
        })
    createTable(localesList)
    await createFilterDropdowns()
}

function reloadToMainPage() {
    window.location.search = ""
}

async function createFilterDropdowns() {
    let dropdowns = document.getElementById("dropdowns");
    let form = document.createElement("form")
    form.action = "/"

    await createCountySelector(form)
    await createMunicipalitySelector(form)
    await createPostalCodeSelector(form)

    let submitButton = document.createElement("input")
    submitButton.type = "submit"
    submitButton.value = "Submit"
    form.appendChild(submitButton)

    let resetButton = document.createElement("button")
    resetButton.innerText = "Reset"
    resetButton.onclick = reloadToMainPage
    form.appendChild(resetButton)

    dropdowns.appendChild(form)

}

async function createCountySelector(element) {
    let counties = await fetch(`/api/counties${window.location.search}`)
        .then(response => {
            return response.json()
        })


    let countySelector = document.createElement("select")
    countySelector.id = "counties"
    countySelector.name = "county"
    let countyLabel = document.createElement("label")
    countyLabel.for = "counties"
    countyLabel.textContent = "Select County"
    countySelector.appendChild(defaultOption())

    for (let index in counties) {
        let county = document.createElement("option")
        county.value = counties[index]
        county.text = counties[index]
        countySelector.appendChild(county)
    }

    element.appendChild(countyLabel)
    element.appendChild(countySelector)
    element.appendChild(document.createElement("br"))
}

async function createMunicipalitySelector(element) {
    let municipalities = await fetch(`/api/municipalities${window.location.search}`)
        .then(response => {
            return response.json()
        })

    let municipalitySelector = document.createElement("select")
    municipalitySelector.id = "municipality"
    municipalitySelector.name = "municipality"
    let municipalityLabel = document.createElement("label")
    municipalityLabel.for = "municipality"
    municipalityLabel.textContent = "Select Municipality"
    municipalitySelector.appendChild(defaultOption())

    for (let index in municipalities) {
        let municipality = document.createElement("option")
        municipality.value = municipalities[index]
        municipality.text = municipalities[index]
        municipalitySelector.appendChild(municipality)
    }

    element.appendChild(municipalityLabel)
    element.appendChild(municipalitySelector)
    element.appendChild(document.createElement("br"))
}

async function createPostalCodeSelector(element) {
    let postalCodes = await fetch(`/api/postalcodes${window.location.search}`)
        .then(response => {
            return response.json()
        })

    let postalCodeSelector = document.createElement("select")
    postalCodeSelector.id = "postalCode"
    postalCodeSelector.name = "postalcode"
    let postalCodeLabel = document.createElement("label")
    postalCodeLabel.for = "postalCode"
    postalCodeLabel.textContent = "Select Postal Code"
    postalCodeSelector.appendChild(defaultOption())

    for (let index in postalCodes) {
        let postalCode = document.createElement("option")
        postalCode.value = postalCodes[index]
        postalCode.text = postalCodes[index]
        postalCodeSelector.appendChild(postalCode)
    }

    element.appendChild(postalCodeLabel)
    element.appendChild(postalCodeSelector)
    element.appendChild(document.createElement("br"))
}

function defaultOption(){

    let defaultOption = document.createElement("option")
    defaultOption.value=""
    defaultOption.selected = true
    defaultOption.disabled = true
    defaultOption.hidden = true
    defaultOption.text = "Select here..."

    return defaultOption
}

function createTable(localesList) {
    let localesElement = document.getElementById("locales");

    let localesTable = document.createElement("table")
    localesTable.setAttribute("id", "localesTable")
    localesTable.appendChild(createHeaders())

    for (let i = 0; i <= localesList.entries.length; i++) {
        localesTable.appendChild(
            createRow(localesList.entries[i])
        )
    }

    localesElement.appendChild(localesTable)
}

function createHeaders() {
    let headerStrings = ["Area", "Address", "Postal Code", "Municipality", "County", "Opening Hours"]

    let tableHeaders = document.createElement("tr")
    for (let value in headerStrings) {
        let x = document.createElement("th")
        x.innerText = headerStrings[value]
        tableHeaders.appendChild(x)
    }

    return tableHeaders
}

function createRow(locale) {
    let rowValues = ["area", "address_line", "postal_code", "municipality_name", "county_name", "opening_hours"]

    let tableRow = document.createElement("tr")
    for (let value in rowValues) {
        if (locale === undefined) {
            continue
        }
        let x = document.createElement("td")
        let valueName = rowValues[value]
        x.innerText = locale[valueName]
        tableRow.appendChild(x)
    }
    return tableRow
}

