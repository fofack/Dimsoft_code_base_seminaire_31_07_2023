import { safeRun } from "../utils.js";

describe('testing utilities functions from utils.js', () => {
    it('the function saferun runs a given function once', async () => {
        let i = 0;
        //simulation d'une fonction avec jest
        const f = jest.fn(() => {
            i++;
        })
        safeRun(f);
        expect(f).toHaveBeenCalledTimes(1);
        expect(i).toBe(1);
    })
})