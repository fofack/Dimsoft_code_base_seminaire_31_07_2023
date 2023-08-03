module.exports = {
    clearMocks: true,
    testEnvironment: 'jsdom',
    testEnvironmentOptions: {
      html: '<html lang="zh-cmn-Hant"></html>',
      url: 'https://jestjs.io/',
      userAgent: 'Agent/007',
    },
    setupFilesAfterEnv: ['regenerator-runtime/runtime', './jest-setup.js'],
    testPathIgnorePatterns: [
        "/node_modules/",
        "/.vscode/",
    ],
    testMatch: ["**/js/__tests__/**/?(*.)+(spec|test).[jt]s?(x)"],
    coverageDirectory: "TestResults/Coverage",
    collectCoverageFrom: [
        "**/js/**/*.{js,jsx}",
    ],
};