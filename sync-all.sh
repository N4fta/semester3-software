# Save the current directory so it can be returned to after the loop is done.
home_dir=$(pwd)

# Find all .git folders in the current directory and its subdirectories
find . -maxdepth 3 -name ".git" | while read git_dir; do
  # Change to the .git folder and go back up one level since git commands cannot be run from a .git folder
  cd "$git_dir/.." || continue

  echo "Syncing $(pwd)"

  git pull -q                                # Pull the latest changes quietly
  git add -A                                 # Add all changes to the staging area
  git commit -q -m "Auto-committing changes" # Commit with a short message
  git push -q                                # Push the changes quietly

  echo ""

  # Return to the home directory
  cd "$home_dir"
done
