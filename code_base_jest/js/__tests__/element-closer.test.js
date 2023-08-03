/**
 * @jest-environment jsdom
 */
import { fireEvent, screen } from '@testing-library/dom'
import { activateClosers } from '../element-closer.js';
import { isElementHidden, showElement } from '../utils.js'

const htmlScen1 = `
                <button class="js-closer" data-to-close="close-me-1" data-testid="controller-1">
                    Close
                </button>
                <div id="close-me-1" data-testid="close-me-1">
                    Data to close and close 
                </div>
                <button class="js-closer" data-to-close="close-me-1" data-testid="controller-11">
                    Close
                </button>
                
                <button class="js-closer" data-to-close="close-me-2" data-testid="controller-2">
                    Close
                </button>
                <div id="close-me-2" data-testid="close-me-2">
                    Data to close
                </div>`

describe('testing elements closers - scenario 1 (the close is well used)', () => {
    beforeEach(() => {
        document.body.innerHTML = htmlScen1;
        activateClosers();
    })

    it('the element is hidden when the click event is fired on its controller', async () => {
        let dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBe(false)

        const button = screen.getByTestId('controller-1')
        fireEvent.click(button)

        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBeTruthy()
    })

    it('the element is hidden when the enter key is pressed on its controller', async () => {
        let dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBe(false)

        const button = screen.getByTestId('controller-1')
        fireEvent.keyPress(button, {key: 'Enter', code: 'Enter', charCode: 13})

        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBeTruthy()
    })

    it('the element is still showing when another key than enter is pressed on its controller', async () => {
        let dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBe(false)

        const button = screen.getByTestId('controller-1')
        fireEvent.keyPress(button, {key: 'Test', code: 'Test', charCode: 13})

        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).not.toBeTruthy()
    })

    it('the element is stil hidden after multiple clicks', async () => {
        let dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBe(false)

        const clickMock = jest.fn((e) => {})
        const button = screen.getByTestId('controller-1')
        button.addEventListener('click', clickMock)
        fireEvent.click(button)
        fireEvent.click(button)
        fireEvent.click(button)
        expect(clickMock).toHaveBeenCalledTimes(3)

        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBeTruthy()
    })

    it('the closer behave well when multiple elements are to close with their respective controllers', async () => {
        let dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBe(false)
        const button = screen.getByTestId('controller-1')
        fireEvent.click(button)
        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBeTruthy()
        showElement(dataToCloseElement)

        dataToCloseElement = screen.getByTestId('close-me-2')
        expect(isElementHidden(dataToCloseElement)).toBe(false)
        const button2 = screen.getByTestId('controller-2')
        fireEvent.click(button2)
        dataToCloseElement = screen.getByTestId('close-me-2')
        expect(isElementHidden(dataToCloseElement)).toBeTruthy()
        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).not.toBeTruthy()
    })

    it('a single element can have multiple close controllers', async () => {
        let dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBe(false)
        const button = screen.getByTestId('controller-1')
        fireEvent.click(button)
        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBeTruthy()
        showElement(dataToCloseElement)

        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBe(false)
        const button11 = screen.getByTestId('controller-11')
        fireEvent.click(button11)
        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBeTruthy()
    })
})


const htmlScen2 = `
                <button class="js-closer" data-to-close="close-me-1" data-testid="controller-1">
                    Close
                </button>
                <div id="close" data-testid="close-me-1">
                    Data to close
                </div>
                
                <button class="js-closer" data-testid="controller-2">
                    Close
                </button>
                <div id="close-me-2" data-testid="close-me-2">
                    Data to close
                </div>
                
                <button class="js-closer" data-to-close="close-me-3" data-testid="controller-3">
                    Close
                </button>
                <div id="close-me-3" data-testid="close-me-3">
                    Data to close
                </div>`

describe('testing elements closers - scenario 2 (the close is not well used)', () => {
    beforeEach(() => {
        document.body.innerHTML = htmlScen2;
    })

    it('no error is raised when data-to-close attribute is missing or data-to-close element is not found', async () => {
        expect(activateClosers).not.toThrowError()
    })

    it('when some elements are malformed, the other well formed elements behave well', async () => {
        activateClosers()
        let dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).toBe(false)
        const button1 = screen.getByTestId('controller-1')
        fireEvent.click(button1)
        dataToCloseElement = screen.getByTestId('close-me-1')
        expect(isElementHidden(dataToCloseElement)).not.toBeTruthy()

        dataToCloseElement = screen.getByTestId('close-me-2')
        expect(isElementHidden(dataToCloseElement)).toBe(false)
        const button2 = screen.getByTestId('controller-2')
        fireEvent.click(button2)
        dataToCloseElement = screen.getByTestId('close-me-2')
        expect(isElementHidden(dataToCloseElement)).not.toBeTruthy()

        dataToCloseElement = screen.getByTestId('close-me-3')
        expect(isElementHidden(dataToCloseElement)).toBe(false)
        const button3 = screen.getByTestId('controller-3')
        fireEvent.click(button3)
        dataToCloseElement = screen.getByTestId('close-me-3')
        expect(isElementHidden(dataToCloseElement)).toBeTruthy()
    })
})