find . -mindepth 1 -maxdepth 3 -type d -name .git -exec git -C . pull \;
