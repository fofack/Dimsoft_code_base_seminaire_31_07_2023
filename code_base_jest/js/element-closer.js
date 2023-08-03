import { hideElement } from './utils.js';

export const activateClosers = function () {
    const closers = document.querySelectorAll('.js-closer');
    closers.forEach((closer) => {
        const elementId = closer.getAttribute('data-to-close');
        const element = document.querySelector('#' + elementId);
        closer.addEventListener('click', (event) => {
            event.preventDefault();
            hideElement(element);
            return false;
        });
        closer.addEventListener('keypress', (event) => {
            if (event.key === "Enter") {
                event.preventDefault();
                hideElement(element);
            }
            return false;
        });
    });
}
