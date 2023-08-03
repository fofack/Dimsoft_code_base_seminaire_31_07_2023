export const hideElement = function (element) {
    if (element && element.classList.contains('hidden')) {
        element.classList.remove('hidden');
    }
    if (element && !element.classList.contains('hidden-important')) {
        element.classList.add('hidden-important');
    }
};

export const showElement = function (element) {
    if (element && element.classList.contains('hidden')) {
        element.classList.remove('hidden');
    }
    if (element && element.classList.contains('hidden-important')) {
        element.classList.remove('hidden-important');
    }
};

export const isElementHidden = function (element) {
    return !element || element.classList.contains("hidden") || element.classList.contains("hidden-important");
}

export const safeRun = function (f) {
    try {
        f();
    } catch (e) { }
}

export const safeRunAll = function (fs) {
    fs.forEach(f => {
        safeRun(f)
    });
}
