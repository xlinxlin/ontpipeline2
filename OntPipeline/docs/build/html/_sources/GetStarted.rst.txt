Get Started
===========
Installation
_______________________________
Anaconda Installation
^^^^^^^^^^^^^^^^^^^^^
Installing on Linux https://docs.anaconda.com/anaconda/install/linux/

Guppy Installation
^^^^^^^^^^^^^^^^^^
.. code-block:: bash

  sudo apt-get update 
  sudo apt-get install wget lsb-release 
  export PLATFORM=$(lsb_release -cs) 
  wget -O- https://mirror.oxfordnanoportal.com/apt/ont-repo.pub | sudo apt-key add - 
  echo "deb http://mirror.oxfordnanoportal.com/apt ${PLATFORM}-stable non-free" | sudo tee /etc/apt/sources.list.d/nanoporetech.sources.list 
  sudo apt-get update
  apt-get install ont-guppy[-cpu]

Porechop Installation
^^^^^^^^^^^^^^^^^^^^^
.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n porechop
   source /opt/anaconda3/bin/activate porechop
   /opt/anaconda3/bin/conda install -c bioconda porechop
   source /opt/anaconda3/bin/deactivate

NanoStat Installation
^^^^^^^^^^^^^^^^^^^^^
.. code-block:: bash

   /opt/anaconda3/bin/conda create -n nanostat
   source /opt/anaconda3/bin/activate nanostat
   /opt/anaconda3/bin/conda install -c bioconda nanostat
   source /opt/anaconda3/bin/deactivate

NanoFilt Installation
^^^^^^^^^^^^^^^^^^^^^
.. code-block:: bash

   /opt/anaconda3/bin/conda create -n nanofilt
   source /opt/anaconda3/bin/activate nanofilt
   /opt/anaconda3/bin/conda install -c bioconda nanofilt
   source /opt/anaconda3/bin/deactivate

Canu Installation
^^^^^^^^^^^^^^^^^
.. code-block:: bash

   /opt/anaconda3/bin/conda create -n canu
   source /opt/anaconda3/bin/activate canu
   /opt/anaconda3/bin/conda install -c bioconda canu
   source /opt/anaconda3/bin/deactivate

Flye Installation
^^^^^^^^^^^^^^^^^
.. code-block:: bash

   /opt/anaconda3/bin/conda create -n flye
   source /opt/anaconda3/bin/activate flye
   /opt/anaconda3/bin/conda install -c bioconda flye
   source /opt/anaconda3/bin/deactivate

Unicycler Installation
^^^^^^^^^^^^^^^^^^^^^^
.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n unicycler
   source /opt/anaconda3/bin/activate unicylcer
   /opt/anaconda3/bin/conda install -c bioconda unicycler
   source /opt/anaconda3/bin/deactivate
