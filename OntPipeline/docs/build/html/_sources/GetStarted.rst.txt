Get Started
===========
Installation
_______________________________
Anaconda Installation
^^^^^^^^^^^^^^^^^^^^^
Installing on Linux https://docs.anaconda.com/anaconda/install/linux/

Guppy3.0.3 Installation
^^^^^^^^^^^^^^^^^^^^^^^
Guppy is a bascalling tool.

.. code-block:: bash

  wget https://mirror.oxfordnanoportal.com/software/analysis/ont-guppy-cpu_3.0.3_linux64.tar.gz
  tar -xf ont-guppy-cpu_3.0.3_linux64.tar.gz
  sudo mv ont-guppy-cpu_3.0.3_linux64 /opt/ont-guppy-cpu_3.0.3

Porechop Installation
^^^^^^^^^^^^^^^^^^^^^

Porechop is a adapter trimming tool.

.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n porechop
   source /opt/anaconda3/bin/activate porechop
   /opt/anaconda3/bin/conda install -c bioconda porechop
   conda deactivate

NanoStat Installation
^^^^^^^^^^^^^^^^^^^^^

NanoStat is a quality control tool.

.. code-block:: bash

   /opt/anaconda3/bin/conda create -n nanostat
   source /opt/anaconda3/bin/activate nanostat
   /opt/anaconda3/bin/conda install -c bioconda nanostat
   conda deactivate

NanoFilt Installation
^^^^^^^^^^^^^^^^^^^^^

NanoStat is a quality control tool.

.. code-block:: bash

   /opt/anaconda3/bin/conda create -n nanofilt
   source /opt/anaconda3/bin/activate nanofilt
   /opt/anaconda3/bin/conda install -c bioconda nanofilt
   conda deactivate


Unicycler Installation
^^^^^^^^^^^^^^^^^^^^^^

Unicycler is an assembly tool.

.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n unicycler
   source /opt/anaconda3/bin/activate unicylcer
   /opt/anaconda3/bin/conda install -c bioconda unicycler
   conda deactivate

BUSCO Installation
^^^^^^^^^^^^^^^^^^

BUSCO is a 

.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n busco
   source /opt/anaconda3/bin/activate busco
   /opt/anaconda3/bin/conda install -c bioconda busco
   conda deactivate

