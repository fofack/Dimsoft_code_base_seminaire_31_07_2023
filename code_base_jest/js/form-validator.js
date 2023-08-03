const MIN_LENGTH_RULE = 'minLength';
const MAX_LENGTH_RULE = 'maxLength';
const MIN_INT_VALUE_RULE = 'minIntValue';
const MAX_INT_VALUE_RULE = 'maxIntValue';
const REGEX_RULE = 'regex';
const ERROR_FIELD = 'errorMessage';

export const checkMinLength = (rule, value, errorElem) => {
    if (value.length < rule[MIN_LENGTH_RULE]) {
        const message = rule[ERROR_FIELD] ? rule[ERROR_FIELD] : `Cette valeur doit contenir au moins ${rule[MIN_LENGTH_RULE]} caractères.`;
        if (errorElem) {
            errorElem.setAttribute('data-error', message);
        }
        return false;
    }
    else
    {
        if (errorElem) {
            errorElem.removeAttribute('data-error');
        }
    }
    return true;
};

export const checkMinIntValue = (rule, value, errorElem) => {
    value = parseInt(value, 10);
    if (!value) {
        if (errorElem) {
            errorElem.setAttribute('data-error', 'Cette valeur doit être un nombre entier');
        }
        return false;
    }
    else
    {
        if (value < rule[MIN_INT_VALUE_RULE]) {
            const message = rule[ERROR_FIELD] ? rule[ERROR_FIELD] : `Cette valeur doit être supérieure ou égale à ${rule[MIN_INT_VALUE_RULE]}`;
            if (errorElem) {
                errorElem.setAttribute('data-error', message);
            }
            return false;
        }
        else
        {
            if (errorElem) {
                errorElem.removeAttribute('data-error');
            }
        }
    }
    return true;
};

export const checkMaxIntValue = (rule, value, errorElem) => {
    value = parseInt(value, 10);
    if (!value) {
        if (errorElem) {
            errorElem.setAttribute('data-error', 'Cette valeur doit être un nombre entier');
        }
        return false;
    }
    else
    {
        if (value > rule[MAX_INT_VALUE_RULE]) {
            const message = rule[ERROR_FIELD] ? rule[ERROR_FIELD] : `Cette valeur doit être inférieure ou égale à ${rule[MAX_INT_VALUE_RULE]}`;
            if (errorElem) {
                errorElem.setAttribute('data-error', message);
            }
            return false;
        }
        else
        {
            if (errorElem) {
                errorElem.removeAttribute('data-error');
            }
        }
    }
    return true;
};

export const checkRegex = (rule, value, errorElem) => {
    if (!rule[REGEX_RULE].test(value)) {
        const message = rule[ERROR_FIELD] ? rule[ERROR_FIELD] : `Le format de cette valeur n'est pas supporté.`;
        if (errorElem) {
            errorElem.setAttribute('data-error', message);
        }
        return false;
    }
    else
    {
        if (errorElem) {
            errorElem.removeAttribute('data-error');
        }
    }
    return true;
};

export const checkFieldValidity = (field) => {
    if (field) {
        const rules = buildRulesForField(field);
        let errorElem = undefined;
        if (field.getAttribute('data-error-elem-id')) {
            errorElem = document.querySelector(`#${field.getAttribute('data-error-elem-id')}`);
        }
        let value = field.value;
        let hasError = false;
        rules.forEach(rule => {
            if (!hasError) {
                if (rule[MIN_LENGTH_RULE]) {
                    hasError = checkMinLength(rule, value, errorElem) ? hasError : true;
                }
                if (rule[MIN_INT_VALUE_RULE]) {
                    hasError = checkMinIntValue(rule, value, errorElem) ? hasError : true;
                }
                if (rule[MAX_INT_VALUE_RULE]) {
                    hasError = checkMaxIntValue(rule, value, errorElem) ? hasError : true;
                }
                if (rule[REGEX_RULE]) {
                    hasError = checkRegex(rule, value, errorElem) ? hasError : true;
                }
            }
        });
        return !hasError;
    }
};

const and = (booleanArray) => {
    let res = true;
    if (booleanArray) {
        booleanArray.forEach((elem) => {
            res = res && elem;
        });
    }
    return res;
};

// Peut être mieux écrit
export const buildRulesForField = (formField) => {
    const rules = [];
    if (formField.getAttribute('data-minlength')) {
        const length = parseInt(formField.getAttribute('data-minlength'), 10);
        const rule = {minLength: length};
        if (formField.getAttribute('data-minlength-error')) {
            rule['errorMessage'] = formField.getAttribute('data-minlength-error');
        }
        rules.push(rule);
    }
    if (formField.getAttribute('data-min-int')) {
        const min = parseInt(formField.getAttribute('data-min-int'), 10);
        const rule = {minIntValue: min};
        if (formField.getAttribute('data-min-int-error')) {
            rule['errorMessage'] = formField.getAttribute('data-min-int-error');
        }
        rules.push(rule);
    }
    if (formField.getAttribute('data-max-int')) {
        const max = parseInt(formField.getAttribute('data-max-int'), 10);
        const rule = {maxIntValue: max};
        if (formField.getAttribute('data-max-int-error')) {
            rule['errorMessage'] = formField.getAttribute('data-max-int-error');
        }
        rules.push(rule);
    }
    if (formField.getAttribute('data-regex')) {
        const _regex = new RegExp(formField.getAttribute('data-regex'), 'g');
        const rule = {regex: _regex};
        if (formField.getAttribute('data-regex-error')) {
            rule['errorMessage'] = formField.getAttribute('data-regex-error');
        }
        rules.push(rule);
    }
    return rules;
};

export const activateFormValidators = () => {
    const forms = document.querySelectorAll('.js-form');
	forms.forEach((form) => {
		form.addEventListener('submit', (event) => {
            const formFields = form.querySelectorAll('.js-form-field');
            const validationResults = [];
            formFields.forEach((formField) => {
                validationResults.push(checkFieldValidity(formField));
            });
			if (and(validationResults)) {
				return true;
			}
			event.preventDefault();
			return false;
		});
	});
};

