Adam Turčan
adam.turcan@drake.sk

Deadline: 5.3.2025

Instructions:

    The homework assignment consists of several tasks.
    Each task represents one scenario.
    The description of each task has two parts:
        First it says what you should assume about the organization of repositories and branches, content of the directory/file tree, and so on.
        Then it specifies what you should accomplish using Git - for example, few higher-level actions or the desired final state.
    Describe your approach: report commands that you would use to accomplish the given tasks, also with a brief explanation in the case of choices that are not obvious (trivial).
        Focus on the usage of Git in your answers. You do not have to report other actions that you performed, such as editing text files.
        Include the output of commands only when explicitly requested.
        Tou can also mention where it is good to be careful (for example, to avoid losing some part of history).
    If you need to validate the overall approach (or to check whether some Git commands do what you expect) during the work on a particular task (scenario), then set up the corresponding environment (repositories, branches) locally on your computer or within MFF GitLab.
    You can write answers in Czech.
    Submit just one text file (report) that contains your answers inline.

Useful links and help:

    http://git-scm.com/doc
    'git' - list of commands (all that you should need for this homework)
    'git help <command>' - detailed syntax and options of a given command

Tasks

    Consider the following initial situation:
        project repository R with two branches, "main" and your current feature branch B
        source code file F with changes (edits), denoted by symbols C1 and C2, at two different locations (e.g., in two procedures)

    Assume that you make two additional changes, C3 and C4, in file F that are completely unrelated (e.g., small edits to yet another procedure or adding some new code). Commit just the changes C4 and C2, in this order, such that you make a separate revision for each of the changes.

    Now consider the following situation (which resembles the previous one):
        project repository R with several branches, including "main" and the branch B where you are implementing some new feature
        source code file F with some edits (changes) that are still work-in-progress (not yet committed into R)
        the current edits in F are together represented by a single changeset C1, and they are not yet ready to be committed

    Assume that your team leader (or customer) asks for a small optimization (enhancement) of the module that includes the file F, that (1) should be realized in a separate branch and (2) should be released immediately after completion. However, you do not want to commit and release the change C1 at this moment, because it is not finished yet, and you want to continue working on C1 just after the small enhancement is done.

    The current active branch B in the local repository has a rather long history, with many commits done in several weeks. Assume the developer makes at least one commit per day. In addition, there are some unfinished modifications to various files in the working directory. Move the local repository and working directory back to the state they were 10 days ago (somehow undo all changes made later). Use symbolic identifiers for versions in the description of your approach, something like "V1" and "V2".

    Here assume the project repository with two branches, (1) "main" shared by all developers and (2) the feature branch B. Furthermore assume the branch B exists for several weeks, and many commits have been made in B. Now is the time when B should be merged into "main". Describe how exactly to merge the branch (what are the necessary steps), if we want to:
        keep the linear history in "main" (if possible), and
        keep the history as clean as possible, in an ideal case having just one summary commit in "main" for the whole branch B.

    Describe all the steps necessary to make a simple bugfix in some large open-source project and publish it (sharing with other developers). Report only actions (commands) relevant to usage of Git (management of repositories, branches and versions). In particular, do not report actions like running tests and editing source code files.

    Consider the following scenario: team of 20 developers with a single manager. The whole team (all developers) work on the same project (software) that consists of a shared core (central framework) and several independent plugins (modules). The software is released quite frequently, every three months. Describe how you would organize repositories and shared branches, policy for branches and merging, and the overall workflow from the perspective of Git usage.

Survey:

We use the survey to evaluate the lectures and homework assignments. It is completely voluntary, but useful for future tuning of the content and level of complexity. Most questions can be answered using the scale from 1 to 10. Write your answer just under the corresponding question.

(1) How new was the topic and content of the lecture for you?
(1 - brand new, 10 - I already knew everything)

     /// 10

(2) Do you think that the content of this lecture was useful?
(1 - useless or too trivial, 10 - very useful)

     /// 10

(3) How do you evaluate the level of complexity of the homework assignment?
(1 - too trivial, 5-6 - just about right, 10 - too demanding or complex)

     /// 6

(4) How do you evaluate your prior experience with the tool in the context of the homework assignment?
(1 - I already know and can use everything, 10 - I tried everything practically for the first time)

     /// 1

(5) How much time (in minutes) did you spend working on the homework assignment?

    //120 cca

(6) Additional space for your own comments on the topic, lecture, and homework assignment:

    // celkom sa mi pacilo zadanie, podla mna uzitocne, no moc som nevedel co tam popisat viac ako detaily

1.  pomocou prikazu `git add -p F` , sa mi spusti patch mod, ktory mi ukazuje ktore tzv hunky chcem stagenut na git,
    ((1/1) Stage this hunk [y,n,q,a,d,e,?]? "takto nejako to vyzera"),
    nasledne si len pri C4 zvolim y a zvysok n, potom spravim `git commit -m "popisok o C4"`.
    toto iste zopakujem ale zvolim y len pri C2 a zase spravim `git commit -m "popisok o C2"`

2.  na branchi kde robim zmenu, teda B spustim prikaz `git stash` (Saved working directory and index state WIP on B: 88c550d add task_1.txt file, "dostanem napr takyto vystup" ),
    nasledne spustim `git checkout main` aby som sa dostal na main branch a potom `git checkout -b <nazov branche, napr enhancement...> `,
    toto ma prepne do nevej branche, kde spravim zmenu a tu nasledne pomocou `git add .` a `git commit -m "sprava o zmene"`, ulozim do repa
    potom zavolam `git checkout B`, teda sa vratim do mojej rozpracovanej branche a tam zavolam `git stash pop`, aby som vytiahol rozpracovane zmeny zo stashu

3.  spustim `git log`, co mi zobrazi historiu commitov. Najdem commit do ktoreho sa chcem vratit a teda zahodit vsetku historiu po nom.
    nazveme tento commit V1, teda jeho identifikator, no a spustime git `reset --hard V1`

4.  v prvom rade pojdem na main branch : `git checkout main` a aktualizujem si ju : `git pull origin main`
    nasledne na mozem ak chcem co najmenej konfliktov pri mergovani ist na branch B: `git checkout B` a spravit rebase voci main branch: `git rebase main`. V pripade konfliktov ich opravim
    spravim git add <opravene subory> a git rebase --continue
    na konci sa vratim naspat na main: `git checkout main` a spravim squash merge: `git merge --squash B`
    (--squash kvoli poziadavke aby som nezaznamenal vsetky zmeny v B) a spravim commit : git commit -m"popis vsetkych zmien " a push : `git push origin main`

5.  najprv si do nejakeho zvoleneho repozitara naklonujem verejne repo, ktore chcem bugfixovat: `git clone <repo=URL>` a presuniem sa do nejakeho
    nastavim si repozitar ako upstream: `git reote add upstream <repo-URL>` a fetchnem si ho: `git fetch upstream`
    nasledne vytvorim novu branch na ktorej budem robit bugfix: `git checkout -b <nazov branche> upstream/main`, tento prikaz ma zaroven aj prepne do tej branche
    spravim bug fix a nasledne ho stagenem a commitnem : `git add` a `git commit -m"info o bug fixe"`
    nakoniec spravim push: git push -u origin <nazov branche> (-u je skratka na --set-upstream)

6.  Spravil by som jeden hlavny repozitar pre cely team, v ktorom by bol projekt pekne rozcleneny do subadresarov.
    boli by 2 hlavne branche main a develop.
    main by bola cisto produkcna vetva, ktora by bola udrziavana s verziami.
    na develop vetve budu jednottlivy clenovia vyvyjat a bugfixovat.
    pri zmenach vzdy budu musiet poriadne okomentovat zmenu ktoru vykonali a pred commitovanim/pushovanim robit pull request aby sa posnazili zamedzit co najviac konfliktom
    pred pravidelnym releasom verzie sa spravi nejaka vetva na finalne testovanie a pripadne opravy chyb ktora sa nasledne mergne s main a aj develop branchov,
    dolezite je ale aby vsetky akcie boli prislusne okomentovane a otagovane
