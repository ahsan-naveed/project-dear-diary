const fs = require("fs");
const readline = require("readline");

async function head() {
  const filename = process.argv[2];
  const fileStream = fs.createReadStream(filename);

  const rl = readline.createInterface({
    input: fileStream,
    crlfDelay: Infinity
  });

  // Note: we use the crlfDelay option to recognize all instances of CR LF
  // ('\r\n') in input.txt as a single line break.

  // read first 10 lines
  let lines = 0;
  rl.on("line", line => {
    if (lines < 10) {
      console.log(line);
    }
    lines++;
  });
}

head();
