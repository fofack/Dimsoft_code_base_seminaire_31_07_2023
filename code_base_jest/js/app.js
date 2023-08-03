import { safeRunAll } from './utils.js';
import { activateClosers } from './element-closer.js';
import { activateFormValidators } from './form-validator.js';


const init = function () {
    // Start by functions that can edit the DOM
    safeRunAll([
        activateClosers,
        activateFormValidators
    ])
};

init();


