const octokit = require("@octokit/rest");
const Configstore = require("configstore");
const _ = require("lodash");
const CLI = require("clui");
const Spinner = CLI.Spinner;
const chalk = require("chalk");

const pkg = require("../package.json");
const inquirer = require("./inquirer");

const conf = new Configstore(pkg.name);

module.exports = {
  getStoreGithubToken: () => {
    return conf.get("github.token");
  },
  registerNewToken: async () => {
    const status = new Spinner("Authenticating you, please wait...");

    try {
      const creds = await inquirer.askGithubCreds();
      const octokit = new octokit({
        type: "basic",
        auth: {
          username: creds.username,
          password: creds.password
        }
      });
      const response = await octokit.oauthAuthorizations.createAuthorization({
        scopes: ["user", "public_repo", "repo", "repo:status"],
        note: "ginits, the command-line tool for initializing Git repos"
      });
      status.start();
      const token = response.data.token;
      if (token) {
        conf.set("github.token", token); // cache the token
        return token;
      } else {
        throw new Error(
          "Missing Token",
          "GitHub token was not found in the response"
        );
      }
    } catch (error) {
      throw error;
    } finally {
      status.stop();
    }
  }
};
