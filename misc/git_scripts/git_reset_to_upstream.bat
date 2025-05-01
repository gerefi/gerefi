git remote add upstream https://github.com/gerefi/gerefi.git

git fetch upstream
git checkout master
git reset --hard upstream/master
git push origin master -f
