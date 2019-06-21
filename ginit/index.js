const chalk = require("chalk");
const clear = require("clear");
const figlet = require("figlet");

const inquirer = require("./lib/inquirer");
const files = require("./lib/files");
const github = require("./lib/github");

// display app name
clear();
console.log(
  chalk.yellow(figlet.textSync("Ginit", { horizontalLayout: "full" }))
);

// check if cwd is already a github repo
if (files.directoryExists(".git")) {
  console.log(chalk.red("Already a git repository!"));
  process.exit();
}

// ask questions
const run = async () => {
  let token = github.getStoreGithubToken();
  if (!token) {
    token = await github.registerNewToken();
  }
  console.log(token);
  process.exit();
};

run();
